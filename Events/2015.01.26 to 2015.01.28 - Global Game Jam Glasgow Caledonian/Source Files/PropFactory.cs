using UnityEngine;
using System.Collections;

public class PropFactory : MonoBehaviour {

    public GameObject[] Props;
    public Spawner[] Spawners;


    public void SpawnInProps(int amount)
    {
        Spawners[Random.Range(0, Spawners.Length)].AddToSpawnQueue(Props[Random.Range(0, Props.Length)]);
    }

    public void MassSpawnProps()
    {
        foreach (Spawner spawner in Spawners)
        {
            spawner.Spawn(Props[Random.Range(0, Props.Length)]);
        }
    }
}
