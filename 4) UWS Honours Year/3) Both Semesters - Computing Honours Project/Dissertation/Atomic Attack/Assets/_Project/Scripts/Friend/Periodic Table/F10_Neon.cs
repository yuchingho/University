using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F10_Neon : AI_Friend {

    [Space( 10), Header("[^ Child: F10_Neon ]")]
    #pragma warning disable
    [SerializeField] int AttackDamage = 100;
    [SerializeField] string Effect = "Lightsaber.";





    void Reset()
    {
        MovementSpeed = 1.40f;
           AttackRate = 1.40f;
           LookRadius = 3.00f;
         AttackRadius = 1.00f;
    }

    void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Enemy" || collision.gameObject.tag == "Friend")
        {   // When F10_Neon plays Attack.anim, will make the DamageArea active briefly to damage Target.
            // Will damage Target's Health with F10_Neon's AttackDamage.
            collision.GetComponent<HealthSystem>().DamageTaken(AttackDamage);

            // Damaging Castle will add to Gold and Score.
            if (collision.gameObject.name == "Castle Health")
            {
                ManagerGame.CurrentScore += AttackDamage * 10;
                ManagerGame.CurrentGold += AttackDamage;
            }
        }
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