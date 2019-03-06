using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F06_Carbon : AI_Friend {

    [Space( 10), Header("[^ Child: F06_Carbon ]")]
    #pragma warning disable
    [SerializeField] string Effect = "Shield";






    void Reset()
    {   // Upgrade = F14_Silicon.
        MovementSpeed = 0.90f;
           AttackRate = 1.00f;
           LookRadius = 3.00f;
         AttackRadius = 0.75f;
    }

    void OnCollisionEnter2D(Collision2D collision)
    {   // not detecting when the sprite has left the ground... else function doesn't work
        if (collision.gameObject.tag == "Ground") { Grounded = true; } else { }
    }
}