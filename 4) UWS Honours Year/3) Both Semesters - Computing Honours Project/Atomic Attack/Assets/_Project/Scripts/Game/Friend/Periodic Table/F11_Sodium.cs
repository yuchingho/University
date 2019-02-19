using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F11_Sodium : F03_Lithium {

    [Space( 10), Header("[^ Child: F11_Sodium ]")]
#pragma warning disable
    [SerializeField] string Downgrade = "F03_Lithium";

    void Reset()
    {
        MovementSpeed = 1f;
        AttackRate = 0.75f;
        LookRadius = 4f;
        AttackRadius = LookRadius;
    }
}