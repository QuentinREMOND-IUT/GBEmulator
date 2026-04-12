package com.remond_quentin.gbemulator.memory

class MemoryBus {
    private val memory = IntArray(65536) // 64KB de mémoire

    fun readByte(address: Int): Int {
        return memory[address] and 0xFF
    }

    fun writeByte(address: Int, value: Int) {
        memory[address] = value and 0xFF
    }

    fun loadROM(data: ByteArray) {
        for (i in data.indices) {
            memory[i] = data[i].toInt() and 0xFF
        }
    }
}