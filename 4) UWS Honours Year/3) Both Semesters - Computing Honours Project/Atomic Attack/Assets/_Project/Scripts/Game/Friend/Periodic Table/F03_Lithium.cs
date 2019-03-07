using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F03_Lithium : F_Gunman {

    [Space( 10), Header("[^ Child: F03_Lithium ]")]
    #pragma warning disable
    [SerializeField] string Effect = "Stunned 1 sec.";






    void Reset()
    {   // Upgrade = F11_Sodium.
        MovementSpeed = 1.00f;
           AttackRate = 2.00f;
           LookRadius = 3.00f;
         AttackRadius = 2.25f;
    }

    void OnCollisionEnter2D(Collision2D collision)
    { if (collision.gameObject.tag == "Ground") { Grounded = true; } GrabbedByMouse = false; }
}