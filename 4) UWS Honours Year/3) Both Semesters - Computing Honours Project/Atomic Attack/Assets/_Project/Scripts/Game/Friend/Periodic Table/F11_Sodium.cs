using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F11_Sodium : F03_Lithium {

    [Space( 10), Header("[^ Child: F11_Sodium ]")]
    #pragma warning disable
    [SerializeField] string Downgrade = "F03_Lithium";






    void Reset()
    {   // Setting colour manually in Inspector + SpriteRenderer.
        MovementSpeed = 1f;
        AttackRate = 1f;
        LookRadius = 6f;
        AttackRadius = 6f;
    }
}