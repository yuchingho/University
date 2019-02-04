using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F11_Sodium : F03_Lithium {

    // Child Class F11_Sodium inheriting from F03_Lithium.
    [Space( 10), Header("[^ Child: F11_Sodium ]")]
    #pragma warning disable
    [SerializeField] GameObject Glow;

    void Reset()
    {
        MovementSpeed = 1f;
        AttackRate = 0.75f;
        LookRadius = 4f;
        AttackRadius = LookRadius;
    }
}