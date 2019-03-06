using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F03_Lithium : F_Gunman {

    [Space( 10), Header("[^ Child: F03_Lithium ]")]
    #pragma warning disable
    [SerializeField] string Effect = "Stunned 1 sec";






    void Reset()
    {   // Upgrade = F11_Sodium.
        MovementSpeed = 1f;
        AttackRate = 0.75f;
        LookRadius = 3.5f;
        AttackRadius = LookRadius;
    }
}