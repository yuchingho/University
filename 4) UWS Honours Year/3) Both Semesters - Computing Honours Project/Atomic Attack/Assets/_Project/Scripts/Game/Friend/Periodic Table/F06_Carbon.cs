using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F06_Carbon : AI_Friend {

    // Child Class F06_Carbon inheriting from AI_Friend.
    [Space( 10), Header("[^ Child:   F06_Carbon ]")]
    #pragma warning disable
    [SerializeField] protected string Upgrade = "Shield";





    void Reset()
    {
        MovementSpeed = 1.2f;
        AttackRate = 0f;
        LookRadius = 3f;
        AttackRadius = 0.55f;
    }

    void OnCollisionEnter2D(Collision2D collision)
    {   // not detecting when the sprite has left the ground... else function doesn't work
        if (collision.gameObject.tag == "Ground") { Grounded = true; } else { }
    }
}