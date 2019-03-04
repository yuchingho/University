using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F14_Silicon : F06_Carbon {

    [Space( 10), Header("[^ Child: F14_Silicon ]")]
    #pragma warning disable
    [SerializeField] protected string Downgrade = "F06_Carbon";






    void Reset()
    {   Unshakeable = true;    // Health = ...
        MovementSpeed = 1f; 
        AttackRate = 1f;
        LookRadius = 3f;
        AttackRadius = 0.5f;
    }

    void OnCollisionEnter2D(Collision2D collision)
    {   // not detecting when the sprite has left the ground... else function doesn't work
        if (collision.gameObject.tag == "Ground") { Grounded = true; } else { }
    }
}