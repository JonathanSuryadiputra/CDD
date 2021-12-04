/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabTwo.AtomicLabTwo;

/**
 * @author  Jonathan Suryadiputra
 * @version 1.0
 * @since   2021-10-11
 * @shortDescription This is a lab to study the different types of locks to prevent race condition
 * @longDescription This lab sheet outlines 3 different types of locks to prevent race condition, namely Atomic Variables, Mutex Locks, as well as the 'synchronized' keyword built in Java. The task is to fix the race condition found in the raw code provided.
 */
class IntegerObj {
    int value;
    IntegerObj(int val) {
        this.value = val;
    }
    int inc(){
        this.value++;
        return this.value;
    }
}
