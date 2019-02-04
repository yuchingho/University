using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F04_Beryllium : F_Gunman {

    // Child Class F04_Beryllium inheriting from F_Gunman.
    [Space( 10), Header("[^ Child: F04_Beryllium ]")]
    #pragma warning disable
    [SerializeField] string Effect = "Blind";

    void Reset()
    {
        MovementSpeed = 1f;
        AttackRate = 0.75f;
        LookRadius = 4f;
        AttackRadius = LookRadius;
    }
}