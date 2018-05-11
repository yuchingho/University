using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class OnEnemyAttackParticleCollision : MonoBehaviour
{
    public AudioClip playerHitAudio;

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag != "EnemyAttackParticle" && collision.gameObject.tag != "EnemyWizard")
        {
            if (collision.gameObject.tag == "Player")
            {
                collision.gameObject.GetComponent<Player>().PlayerHurt(5); // Take off health
                SoundManager.instance.PlaySingle(playerHitAudio);
            }
            Destroy(gameObject);
        }
    }
}