using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F18_Argon : AI_Friend {

    [Space( 10), Header("[^ Child: F18_Argon ]")]
    #pragma warning disable
    [SerializeField] string Effect = "Burn DoT";






    void Reset()
    {     Unshakeable = true;
        MovementSpeed = 0.80f;
           AttackRate = 0.00f;
           LookRadius = 3.00f;
         AttackRadius = 3.00f;
    }

    void OnCollisionEnter2D(Collision2D collision)
    { if (collision.gameObject.tag == "Ground") { Grounded = true; } GrabbedByMouse = false; }
}