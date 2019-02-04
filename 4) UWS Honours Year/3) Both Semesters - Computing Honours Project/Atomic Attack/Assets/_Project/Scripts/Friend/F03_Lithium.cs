using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F03_Lithium : F_Gunman {

    // Child Class F03_Lithium inheriting from F_Gunman.
    [Space( 10), Header("[^ Child: F03_Lithium ]")]
    #pragma warning disable
    [SerializeField] string Effect = "Stun";

    void Reset()
    {
        MovementSpeed = 1f;
        AttackRate = 0.75f;
        LookRadius = 4f;
        AttackRadius = LookRadius;
        // if stunned, animation.enabled=false;
    }
}