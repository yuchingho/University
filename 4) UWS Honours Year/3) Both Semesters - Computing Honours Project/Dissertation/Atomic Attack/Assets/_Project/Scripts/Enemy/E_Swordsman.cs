﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class E_Swordsman : AI_Enemy {

    // Child Class E_Swordsman inheriting from AI_Enemy.
    [Space( 10), Header("[^ Child:   E_Swordsman ]")]
    #pragma warning disable
    [SerializeField] protected int AttackDamage = 1;





    void Reset()
    {
        MovementSpeed = 1.20f;
           AttackRate = 1.00f;
           LookRadius = 3.00f;
         AttackRadius = 0.85f;
    }

    protected override void Movement()
    {   // Activating Blinded.
        if (Blinded == true)  { AttackRate = 0; }
        if (Blinded == false) { AttackRate = 1; }
        base.Movement();
    }

    void OnTriggerEnter2D(Collider2D collision)
    {   // Bugs: Doesn't damage f_swordsman or F01_Hydrogen.
        if (collision.gameObject.tag == "Friend")
        {   // When E_Swordsman plays Attack.anim, will make the DamageArea active briefly to damage Target.
            // Will damage Target's Health with E_Swordsman's AttackDamage.
            collision.GetComponent<HealthSystem>().DamageTaken(AttackDamage);
        }
    }

    void OnCollisionExit2D(Collision2D collision)
    {   // As lil guy walks off Castle Platform, switching layers.
        if (collision.gameObject.tag == "Castle") { OnTheCastle = false; Grounded = false; gameObject.layer = 8; }
    }

    void OnCollisionEnter2D(Collision2D collision)
    {   // Switching layers so can walk out of Castle if chucked on it.
        if (collision.gameObject.tag == "Ground")
        {
            Grounded = true;
            OnTheCastle = false;
            gameObject.layer = 8;
            if (GrabbedByMouse == true)
            { StartCoroutine(HitTheGround()); }
        }

        if (collision.gameObject.tag == "Castle")
        {
            Grounded = true;
            OnTheCastle = true;
            gameObject.layer = 12;
            GrabbedByMouse = false;
        }
    }
}