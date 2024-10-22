﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F11_Sodium : F03_Lithium {

    [Space( 10), Header("[^ Child: F11_Sodium ]")]
    #pragma warning disable
    [SerializeField] string Downgrade = "F03_Lithium.";






    void Reset()
    {
        MovementSpeed = 1.20f;
           AttackRate = 0.75f;
           LookRadius = 3.50f;
         AttackRadius = 3.50f;
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground")
        {
            Grounded = true;
            if (transform.position.x >= -9 && GrabbedByMouse == true)
            {
                gameObject.layer = 9;
                StartCoroutine(HitTheGround());
            }
        }
    }
}