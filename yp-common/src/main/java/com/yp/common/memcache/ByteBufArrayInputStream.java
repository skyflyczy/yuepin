/**
 * Copyright (c) 2007 Greg Whalin
 * All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the BSD license
 *
 * This library is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.
 *
 * You should have received a copy of the BSD License along with this
 * library.
 *
 * @author greg whalin <greg@meetup.com> 
 * @version 2.0
 */
package com.yp.common.memcache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public final class ByteBufArrayInputStream extends InputStream implements LineInputStream {
	private ByteBuffer[] bufs;
	private int currentBuf = 0;
	private byte lastCode;
	
	public ByteBufArrayInputStream( List<ByteBuffer> bufs ) throws Exception {
		this( bufs.toArray( new ByteBuffer[] {} ) );
	}
	
	public ByteBufArrayInputStream( ByteBuffer[] bufs ) throws Exception {
		if ( bufs == null || bufs.length == 0 )
			throw new Exception( "buffer is empty" );
		
		this.bufs = bufs;
		for ( ByteBuffer b : bufs )
			b.flip();
	}
	
	public int read() {
		do {
			if ( bufs[currentBuf].hasRemaining() )
				return bufs[currentBuf].get();
			currentBuf++;
		}
		while ( currentBuf < bufs.length );
		
		currentBuf--;
		return -1;
	}
	
	public int read( byte[] buf ) {
		int len = buf.length;
		int bufPos = 0;
		do {
			if ( bufs[currentBuf].hasRemaining() ) {
				int n = Math.min( bufs[currentBuf].remaining(), len-bufPos );
				bufs[currentBuf].get( buf, bufPos, n );
				bufPos += n;
			}
			currentBuf++;
		}
		while ( currentBuf < bufs.length && bufPos < len );
		
		currentBuf--;
		
		if ( bufPos > 0 || ( bufPos == 0 && len == 0 ) )
			return bufPos;
		else
			return -1;
	}
	
	public String readLine() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		do {
			if ( bufs[currentBuf].hasRemaining() )
				if(readEOL(bos)) break;
			currentBuf++;
		}
		while ( currentBuf < bufs.length );
		return bos.toString().trim();
	}
	
	private boolean readEOL(ByteArrayOutputStream bos) throws IOException {
		boolean result = false;
		int index = -2;
		byte[] line = bufs[currentBuf].array();
		
		for(int i = bufs[currentBuf].position() ; i < bufs[currentBuf].limit(); i++)
		{
			if(i != bufs[currentBuf].limit() - 1 && line[i] == 13 && line[i+1] == 10) {
				index = i;
				break;
			}
			// if we got '\r' on last page, and got '\n' on this page
			if(lastCode == 13 && line[i] == 10) {
				index = -1;
				break;
			}
		}
		
		if (index >= 0)
		{	
			int len = index - bufs[currentBuf].position();
			
			byte[] data = new byte[len];
			bufs[currentBuf].get(data, 0, len);
			bufs[currentBuf].get(new byte[2]); // skip '\r\n'
			bos.write(data);
			result = true;
		}
		else if(index == -1) {
			byte[] newBos = bos.toByteArray();
			bufs[currentBuf].get(new byte[1]); // skip '\n'
			if(newBos.length > 0) {
				bos.reset();
				bos.write(newBos, 0, newBos.length-1);
			}
			result = true;
		}
		else
		{
			byte[] data = new byte[bufs[currentBuf].remaining()]; 
			bufs[currentBuf].get(data);
			bos.write(data);
			lastCode = data[data.length-1];
		}
		return result;
	}
	

	public void clearEOL() throws IOException {
		byte[] b = new byte[1];
		boolean eol = false;
		while ( read( b, 0, 1 ) != -1 ) {
		
			// only stop when we see
			// \r (13) followed by \n (10)
			if ( b[0] == 13 ) {
				eol = true;
				continue;
			}
			
			if ( eol ) {
				if ( b[0] == 10 )
					break;
				eol = false;
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder( "ByteBufArrayIS: " );
		sb.append( bufs.length ).append( " bufs of sizes: \n" );

		for ( int i=0; i < bufs.length; i++ ) {
			sb.append( "                                        " )
				.append (i ).append( ":  " ).append( bufs[i] ).append( "\n" );
		}
		return sb.toString();
	}
}
