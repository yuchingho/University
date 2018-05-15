using UnityEngine;
using System.Collections;

public class KinSetter : MonoBehaviour {

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.CompareTag("Player"))
        {
            //FindObjectOfType<PropFactory>().MassSpawnProps();
            //FindObjectOfType<EnemyFactory>().MassSpawnEnemy();

            //Game.GameOver(false);
            //Destroy(this);
        }

        collision.rigidbody.isKinematic = true;
    }
}
