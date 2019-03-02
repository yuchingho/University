using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F04_Beryllium : F_Gunman {

    [Space( 10), Header("[^ Child: F04_Beryllium ]")]
    #pragma warning disable
    [SerializeField] int DurationBlind = 2;






    void Reset()
    {   // Upgrade = F12_Magnesium.
        MovementSpeed = 1f;
        AttackRate = 0.75f;
        LookRadius = 4f;
        AttackRadius = LookRadius;
    }
}