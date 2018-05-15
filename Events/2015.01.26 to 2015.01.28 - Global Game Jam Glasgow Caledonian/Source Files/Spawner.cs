using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class Spawner : MonoBehaviour {

    const float minTimeBetweenSpawns = 2f;
    const float maxTimeBetweenSpawns = 5f;

    public Vector2 Force = Vector3.zero;
    IList<GameObject> spawnQueue = new List<GameObject>();
    float timeSinceLastSpawn = 0;
    float timeTillNextSpawn = 0;
    Rigidbody2D PlayerBody;

    void Start()
    {
        SetTimeTillNextSpawn();
        PlayerBody = Game.PlayerBody.rigidbody2D;
    }

    public void AddToSpawnQueue(GameObject obj)
    {
        spawnQueue.Add(obj);
        if (obj.CompareTag("Enemy"))
        {
            Game.NumberOfEnemys++;
        }
        else
        {
            Game.NumberOfProps++;
        }
    }

    void SetTimeTillNextSpawn()
    {
        timeTillNextSpawn = Random.Range(minTimeBetweenSpawns, maxTimeBetweenSpawns);
    }

    public void Spawn(GameObject objToSpawn)
    {
        GameObject newObj = Instantiate(objToSpawn) as GameObject;

        newObj.transform.position = transform.position;
        newObj.rigidbody2D.velocity = PlayerBody.velocity;

        if (newObj.CompareTag("Enemy"))
        {
            newObj.rigidbody2D.AddForce(new Vector2(0, -600f));
        }
        newObj.rigidbody2D.AddForce(Force);
        newObj.AddComponent<Bounds>().IsMainObject = true;

        HingeJoint2D[] bodyParts = newObj.GetComponentsInChildren<HingeJoint2D>();
        foreach (HingeJoint2D bodyPart in bodyParts)
        {
            bodyPart.gameObject.AddComponent<Bounds>();
        }

        timeSinceLastSpawn = 0;
    }

    void Update()
    {
        timeSinceLastSpawn += Time.deltaTime;
        if(spawnQueue == null || spawnQueue.Count < 1)
        {
            return;
        }

        if (timeSinceLastSpawn >= timeTillNextSpawn)
        {
            Spawn(spawnQueue[0]);
            spawnQueue.RemoveAt(0);
        }
    }
}
