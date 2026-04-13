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