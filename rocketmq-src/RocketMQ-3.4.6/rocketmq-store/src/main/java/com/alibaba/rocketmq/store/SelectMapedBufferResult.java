/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.alibaba.rocketmq.store;

import java.nio.ByteBuffer;


/**
 * @author shijia.wxr  SelectMapedBufferResult���ȡ������mapedFile�д�startOffset��ʼ��size�ֽ����ݣ���size�ֽ����ݴ���byteBuffer����MapedFile.selectMapedBuffer
 */
public class SelectMapedBufferResult {
    //mapfile��������ʼλ��
    private final long startOffset;
    //��ȡ����bytebuffer.
    private final ByteBuffer byteBuffer;
    //�ֽ�����ĳ��ȡ�
    private int size;
    //���ĸ�mapfile�ļ�
    private MapedFile mapedFile;

    //����mapedFile�д�startOffset��ʼ��size�ֽ����ݣ�������byteBuffer
    public SelectMapedBufferResult(long startOffset, ByteBuffer byteBuffer, int size, MapedFile mapedFile) {
        this.startOffset = startOffset;
        this.byteBuffer = byteBuffer;
        this.size = size;
        this.mapedFile = mapedFile;
    }


    public ByteBuffer getByteBuffer() {
        return byteBuffer;
    }


    public int getSize() {
        return size;
    }


    public void setSize(final int s) {
        this.size = s;
        this.byteBuffer.limit(this.size);
    }


    public MapedFile getMapedFile() {
        return mapedFile;
    }


    @Override
    protected void finalize() {
        if (this.mapedFile != null) {
            this.release();
        }
    }

    public synchronized void release() {
        if (this.mapedFile != null) {
            this.mapedFile.release();
            this.mapedFile = null;
        }
    }

    public long getStartOffset() {
        return startOffset;
    }
}