using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawnSystem : MonoBehaviour {

    public int minEnemies;
    public int maxEnemies;
    public int maxEnemiesPerWave = 20;
    public int currentEnemies;
    public int currentWave;
    public float spawnInterval;
    public float random;
    public int wytches = 0;
    public int totalEnemiesForThisRound = 50; // Max enemies to have for the whole round 
    public int enemiesSpawnedThisWave;// here it would be like how many to spawn ie not max enemies but a threshold 
    public Transform[] spawnPoints;

    [System.Serializable]
    public class Enemy
    {
        public string enemyName;
        public int chanceToSpawn;
        public GameObject enemy;
    }

    public Enemy[] enemies = new Enemy[11];

    void Start()
    {
        currentWave = 1;
        totalEnemiesForThisRound = 50;
        Waves();
        StartCoroutine(SpawnLoop());
    }

    IEnumerator SpawnLoop()
    {
        while (true)
        {
            SpawnEnemies();
            yield return new WaitForSeconds(spawnInterval);
        }
    }

    void Update()
    {
        if (enemiesSpawnedThisWave == totalEnemiesForThisRound)
        {
            enemiesSpawnedThisWave = 0;
            totalEnemiesForThisRound += 20;
            currentWave += 1;
            Waves();
        }
    }

    void SpawnEnemies() // start creating some enemies
    {
        if (currentEnemies <= maxEnemiesPerWave && enemiesSpawnedThisWave != totalEnemiesForThisRound)
        {
            random = Random.Range(0, 100); // draw a number between 0 and 99
            int lowLim;    // lowLim and hiLim are automatically set for each enemy
            int hiLim = 0;
            for (int l_enemy = 0; l_enemy < enemies.Length; l_enemy++)
            {
                lowLim = hiLim; // set low limit...
                hiLim += enemies[l_enemy].chanceToSpawn; // and high limit
                if (random >= lowLim && random < hiLim)
                { // instantiate it!
                    Debug.Log(random);
                    Transform l_obj = spawnPoints[Random.Range(0, spawnPoints.Length)];
                    Instantiate(enemies[l_enemy].enemy, l_obj.position, l_obj.rotation);
                    currentEnemies += 1;
                    enemiesSpawnedThisWave += 1;
                }
            }
        }
    }

    void Waves()
    {
        switch (currentWave) // define the percentage for each enemy
        {
            case 1:
                enemies[0].chanceToSpawn = 50;
                enemies[1].chanceToSpawn = 50;
                break;
            case 2:
                enemies[0].chanceToSpawn = 30;
                enemies[1].chanceToSpawn = 30;
                break;
            default:
                break;
        }
    }
}