using UnityEngine;
using System.Collections;

public class EnemyFactory : MonoBehaviour {

    public GameObject[] Enemys;
    public Spawner[] Spawners;


    public void SpawnInEnemy(int amount)
    {
        for (int i = 0; i < amount; i++)
        {
            Spawners[Random.Range(0, Spawners.Length)].AddToSpawnQueue(Enemys[Random.Range(0, Enemys.Length)]);
        }
    }

    public void MassSpawnEnemy()
    {
        foreach (Spawner spawner in Spawners)
        {
            for (int i = 0; i < 5; i++)
            {
                spawner.Spawn(Enemys[Random.Range(0, Enemys.Length)]);
            }
        }
    }
}
