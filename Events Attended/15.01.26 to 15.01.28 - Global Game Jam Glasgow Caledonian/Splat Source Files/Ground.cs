using UnityEngine;
using System.Collections;

public class Ground : MonoBehaviour {

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.CompareTag("Player"))
        {
            Game.MaxEnemys = 10;
            Game.MaxProps = 10;


            //FindObjectOfType<PropFactory>().MassSpawnProps();
            //FindObjectOfType<EnemyFactory>().MassSpawnEnemy();
            
            Game.GameOver();
        }

        collision.rigidbody.isKinematic = true;
        collision.gameObject.AddComponent<Ground>();
        collision.gameObject.layer = LayerMask.NameToLayer("Ground");
    }

    void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.CompareTag("Player"))
        {
            foreach (Clouds cloud in FindObjectsOfType<Clouds>())
            {
                cloud.enabled = false;
            }
        }

        RandomMovement rMove = collision.GetComponent<RandomMovement>();
        if (rMove)
        {
            Destroy(rMove);
        }

    }
}
