using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F12_Magnesium : F04_Beryllium {

    [Space( 10), Header("[^ Child: F12_Magnesium ]")]
    #pragma warning disable
    [SerializeField] string Downgrade = "F04_Beryllium.";






    void Reset()
    {
        MovementSpeed = 1.20f;
           AttackRate = 0.75f;
           LookRadius = 4.00f;
         AttackRadius = 4.00f;
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