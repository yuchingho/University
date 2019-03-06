using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F04_Beryllium : F_Gunman {

    [Space( 10), Header("[^ Child: F04_Beryllium ]")]
    #pragma warning disable
    [SerializeField] string Effect = "Blinded 2 sec.";






    void Reset()
    {   // Upgrade = F12_Magnesium.
        MovementSpeed = 1.00f;
           AttackRate = 2.00f;
           LookRadius = 3.75f;
         AttackRadius = 3.75f;
    }
}