using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F18_Argon : AI_Friend {

    [Space( 10), Header("[^ Child: F18_Argon ]")]
    #pragma warning disable
    [SerializeField] string Upgrade = "Burn";






    void Reset()
    {
        MovementSpeed = 0.7f;
        AttackRate = 0f;
        LookRadius = 3.5f;
        AttackRadius = LookRadius;
    }
}