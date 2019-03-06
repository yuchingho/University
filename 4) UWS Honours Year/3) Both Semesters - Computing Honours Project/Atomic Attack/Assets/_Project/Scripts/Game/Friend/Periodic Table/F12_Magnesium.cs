using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F12_Magnesium : F04_Beryllium {

    [Space( 10), Header("[^ Child: F12_Magnesium ]")]
    #pragma warning disable
    [SerializeField] string Downgrade = "F04_Beryllium";






    void Reset()
    {
        MovementSpeed = 1.20f;
           AttackRate = 1.10f;
           LookRadius = 4.50f;
         AttackRadius = 4.50f;
    }
}