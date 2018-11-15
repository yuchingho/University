using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ManagerSpawn : MonoBehaviour {

    ManagerGame ManagerGame;    // Getting ManagerGame.cs
    public bool AllBlocksSpawned;
    public bool AllBlocksDestroyed;
    public enum SpawnState { Spawning, Waiting };
    public SpawnState State = SpawnState.Waiting;
    public Transform[] SpawnPoints;
    int SpawnIndex;

    [System.Serializable]
    public class Blocks
    {
        public string BlockName;
        public GameObject BlockPrefab;
        public int SpawnChance;
    }

    public Blocks[] Beginner = new Blocks[6];
    public Blocks[] Easy = new Blocks[6];

    void Start()
    {
        ManagerGame = GameObject.Find("ManagerGame").GetComponent<ManagerGame>();
        AllBlocksSpawned = false;
        AllBlocksDestroyed = false;
        SpawnIndex = 0;
        StartCoroutine(SpawnLoop());
    }

    void Update()
    {   // Counting all Blocks in scene, then checking if Blocks have been destroyed, resetting BlocksFilled.
        GameObject[] ActiveBlocks = GameObject.FindGameObjectsWithTag("block");
        if (ActiveBlocks.Length == 27) { AllBlocksSpawned = true; AllBlocksDestroyed = false; }
        if (ActiveBlocks.Length ==  0) { AllBlocksSpawned = false; AllBlocksDestroyed = true; }

        // still need to respawn when all destroyed
    }

    IEnumerator SpawnLoop()
    {
        while (true)
        {
            SpawnBlocks();
            yield return new WaitForSeconds(0.1f);
        }
    }

    void SpawnBlocks()
    {
        State = SpawnState.Spawning;
        int RangeLowest;        // Choose random lowest number. 
        int RangeHighest = 0;   // Choose random highest number.
        float RangeBetween = Random.Range(0, 100);  // Choose a random number inbetween highest and lowest.

        // if moving up difficulties...
        // player not being able to move while it's spawning

        for (int i = 0; i < Beginner.Length; i++)
        {
            RangeLowest = RangeHighest;
            RangeHighest += Beginner[i].SpawnChance;
            if (RangeBetween >= RangeLowest && RangeBetween < RangeHighest && SpawnIndex < SpawnPoints.Length)
            {   // If RangeBetween is below the highest and lowest, and SpawnIndex to remove IndexOutOfBounds.
                Transform PositionToSpawn = SpawnPoints[SpawnIndex];    // Spawn random prefab at SpawnPoints.position.
                Instantiate(Beginner[i].BlockPrefab, PositionToSpawn.position, Quaternion.identity);
                SpawnIndex++;   // Through every iteration, SpawnPoints[i++].
            }
            else { State = SpawnState.Waiting; }
        }
    }
}