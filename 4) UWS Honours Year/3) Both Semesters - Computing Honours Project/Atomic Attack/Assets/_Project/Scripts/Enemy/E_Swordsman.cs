using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class E_Swordsman : AI_Enemy {

    // Child Class E_Swordsman inheriting from AI_Enemy.
    [Space( 10), Header("[^ Child:   E_Swordsman ]")]
    #pragma warning disable
    [SerializeField] protected int AttackDamage = 1;





    void Reset()
    {
        MovementSpeed = 1.2f;
        AttackRate = 1f;
        LookRadius = 3f;
        AttackRadius = 0.85f;
    }

    void OnCollisionEnter2D(Collision2D collision)
    {   // Switching layers so can walk out of Castle if chucked on it.
        if (collision.gameObject.tag == "Ground") { OnCastle = false; Grounded = true; gameObject.layer = 8; }
        if (collision.gameObject.tag == "Castle") { OnCastle = true;  Grounded = true; gameObject.layer = 12; }
    }

    void OnCollisionExit2D(Collision2D collision)
    {   // As lil guy walks off Castle Platform, switching layers.
        if (collision.gameObject.tag == "Castle") { OnCastle = false;  Grounded = false; gameObject.layer = 8; }
    }

    void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Friend")
        {   // When E_Swordsman plays Attack.anim, will make the DamageArea active briefly to damage Target.
            // Will damage Target's Health with E_Swordsman's AttackDamage.
            collision.GetComponent<HealthSystem>().DamageTaken(AttackDamage);

            // Bugs
            // Doesn't damage f_swordsman   (because same sprite size?)
            // Doesn't damage hydrogen      (because same sprite size?)

            // Damages f_gunman fine.
            // Damages lithium, but runs in wrong direction when shot at
            // After being chucked in air, runs in wrong direction
        }
    }
}