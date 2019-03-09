using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F14_Silicon : F06_Carbon {

    [Space( 10), Header("[^ Child: F14_Silicon ]")]
    #pragma warning disable
    [SerializeField] string Downgrade = "F06_Carbon.";






    void Reset()
    {     Unshakeable = true;
        MovementSpeed = 1.00f; 
           AttackRate = 1.00f;
           LookRadius = 3.00f;
         AttackRadius = 0.65f;
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