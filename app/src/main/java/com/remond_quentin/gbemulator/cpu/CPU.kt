package com.remond_quentin.gbemulator.cpu
import com.remond_quentin.gbemulator.memory.MemoryBus

class CPU (private val bus: MemoryBus) {
    // Registres 8 bits
    var a: Int = 0
    var b: Int = 0
    var c: Int = 0
    var d: Int = 0
    var e: Int = 0
    var f: Int = 0
    var h: Int = 0
    var l: Int = 0

    // Registres 16 bits
    var sp: Int = 0 // Stack Pointer
    var pc: Int = 0 // Program Counter

    // Registres paires (combinaisons 16 bits)
    var af: Int
        get() = (a shl 8) or f
        set(value) { a = (value shr 8) and 0xFF; f = value and 0xFF }

    var bc: Int
        get() = (b shl 8) or c
        set(value) { b = (value shr 8) and 0xFF; c = value and 0xFF }

    var de: Int
        get() = (d shl 8) or e
        set(value) { d = (value shr 8) and 0xFF; e = value and 0xFF }

    var hl: Int
        get() = (h shl 8) or l
        set(value) { h = (value shr 8) and 0xFF; l = value and 0xFF }

    // Flags (bits du registre F)
    var flagZ: Boolean
        get() = (f and 0x80) != 0
        set(value) { f = if (value) f or 0x80 else f and 0x80.inv() }

    var flagN: Boolean
        get() = (f and 0x40) != 0
        set(value) { f = if (value) f or 0x40 else f and 0x40.inv() }

    var flagH: Boolean
        get() = (f and 0x20) != 0
        set(value) { f = if (value) f or 0x20 else f and 0x20.inv() }

    var flagC: Boolean
        get() = (f and 0x10) != 0
        set(value) { f = if (value) f or 0x10 else f and 0x10.inv() }

    fun fetch(): Int {
        val opcode = bus.readByte(pc)
        pc++
        return opcode
    }
}