using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class OnAttackParticleCollision : MonoBehaviour
{
    public AudioClip wizardHitAudio;

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag != "AttackParticle" && collision.gameObject.tag != "Player")
        {
            if (collision.gameObject.tag == "EnemyWizard")
            {
                collision.gameObject.GetComponent<EnemyHealth>().EnemyHurt(5); // Take off health
                SoundManager.instance.PlaySingle(wizardHitAudio);
            }

            Destroy(gameObject);
        }
    }
}