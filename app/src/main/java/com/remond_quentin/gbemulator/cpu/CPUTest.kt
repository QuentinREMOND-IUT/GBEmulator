package com.remond_quentin.gbemulator.cpu

import com.remond_quentin.gbemulator.memory.MemoryBus

fun testCPU() {
    val bus = MemoryBus()
    val cpu = CPU(bus)

    // On écrit un programme simple en mémoire
    // LD A, 42  (charger la valeur 42 dans A)
    bus.writeByte(0x0000, 0x3E) // opcode LD A, n
    bus.writeByte(0x0001, 42)   // la valeur 42

    // LD B, 10  (charger la valeur 10 dans B)
    bus.writeByte(0x0002, 0x06) // opcode LD B, n
    bus.writeByte(0x0003, 10)   // la valeur 10

    // On exécute 2 instructions
    cpu.step()
    cpu.step()

    // On vérifie les résultats
    assert(cpu.a == 42) { "A devrait être 42, mais vaut ${cpu.a}" }
    assert(cpu.b == 10) { "B devrait être 10, mais vaut ${cpu.b}" }

    println("Test CPU réussi !")
}

fun testADD() {
    val bus = MemoryBus()
    val cpu = CPU(bus)

    // LD A, 200
    bus.writeByte(0x0000, 0x3E)
    bus.writeByte(0x0001, 200)

    // LD B, 100
    bus.writeByte(0x0002, 0x06)
    bus.writeByte(0x0003, 100)

    // ADD A, B
    bus.writeByte(0x0004, 0x80)

    cpu.step() // LD A, 200
    cpu.step() // LD B, 100
    cpu.step() // ADD A, B

    // 200 + 100 = 300, mais sur 8 bits = 44 (300 - 256)
    assert(cpu.a == 44) { "A devrait être 44, mais vaut ${cpu.a}" }
    assert(cpu.flagC) { "flagC devrait être true (débordement)" }
    assert(!cpu.flagZ) { "flagZ devrait être false" }

    println("Test ADD réussi !")
}

fun testSUB() {
    val bus = MemoryBus()
    val cpu = CPU(bus)

    // LD A, 5
    bus.writeByte(0x0000, 0x3E)
    bus.writeByte(0x0001, 5)

    // LD B, 10
    bus.writeByte(0x0002, 0x06)
    bus.writeByte(0x0003, 10)

    // SUB A, B
    bus.writeByte(0x0004, 0x90)

    cpu.step()
    cpu.step()
    cpu.step()

    // 5 - 10 = -5, sur 8 bits = 251
    assert(cpu.a == 251) { "A devrait être 251, mais vaut ${cpu.a}" }
    assert(cpu.flagC) { "flagC devrait être true (débordement)" }
    assert(cpu.flagN) { "flagN devrait être true (soustraction)" }

    println("Test SUB réussi !")
}