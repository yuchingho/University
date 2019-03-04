using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F06_Carbon : AI_Friend {

    [Space( 10), Header("[^ Child: F06_Carbon ]")]
    #pragma warning disable
    [SerializeField] protected string Upgrade = "Shield";






    void Reset()
    {   // Upgrade = F14_Silicon. Health = ...
        MovementSpeed = 1f;
        AttackRate = 1f;
        LookRadius = 3f;
        AttackRadius = 0.7f;
    }

    void OnCollisionEnter2D(Collision2D collision)
    {   // not detecting when the sprite has left the ground... else function doesn't work
        if (collision.gameObject.tag == "Ground") { Grounded = true; } else { }
    }
}