using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F11_Sodium : F03_Lithium {

    [Space( 10), Header("[^ Child: F11_Sodium ]")]
    #pragma warning disable
    [SerializeField] string Downgrade = "F03_Lithium";






    void Reset()
    {
        MovementSpeed = 1.20f;
           AttackRate = 1.10f;
           LookRadius = 4.50f;
         AttackRadius = 4.50f;
    }
}