using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F18_Argon : AI_Friend {

    [Space( 10), Header("[^ Child: F18_Argon ]")]
    #pragma warning disable
    [SerializeField] string Effect = "Burn DoT";






    void Reset()
    {   Unshakeable = true;
        MovementSpeed = 0.7f;
        AttackRate = 0f;
        LookRadius = 1.3f;
        AttackRadius = LookRadius;
    }

    void OnCollisionEnter2D(Collision2D collision)
    {   // not detecting when the sprite has left the ground... else function doesn't work
        if (collision.gameObject.tag == "Ground") { Grounded = true; } else { }
    }
}