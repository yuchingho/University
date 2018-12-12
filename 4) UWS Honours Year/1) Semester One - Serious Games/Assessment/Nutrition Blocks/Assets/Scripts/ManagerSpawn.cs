using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ManagerSpawn : MonoBehaviour {

    ManagerGame ManagerGame;    // Getting the ManagerGame Script.
    public Text UIDifficulty;
    public Transform[] SpawnPoints;
    public int SpawnIndex;

    [System.Serializable]
    public class Blocks
    {
        public string BlockName;
        public GameObject BlockPrefab;
        public int SpawnChance;
    }

    public Blocks[] Beginner = new Blocks[6];
    public Blocks[] Easy = new Blocks[6];
    public Blocks[] Medium = new Blocks[26];
    public Blocks[] Hard = new Blocks[26];

    void Start()
    {
        ManagerGame = GameObject.Find("ManagerGame").GetComponent<ManagerGame>();
        SpawnIndex = 0;
        StartCoroutine(SpawnLoop());
        // Initalising SpawnLoop().
    }

    void Update()
    {   // Counting all Blocks in scene, then checking if Blocks have been destroyed.
        GameObject[] ActiveBlocks;
        ActiveBlocks = GameObject.FindGameObjectsWithTag("block");
        if (ActiveBlocks.Length <= 0)
        {   // If all Blocks destroyed, reset SpawnIndex so SpawnLoop() can run again.
            ManagerGame.PlayerIsPlaying = false;
            SpawnIndex = 0;
            StartCoroutine(SpawnLoop());
        }
        UIDifficulty.text = ManagerGame.Difficulty.ToString();
    }

    IEnumerator SpawnLoop()
    {   // While SpawnIndex <= SpawnPoints.Length, SpawnBlocks will run.
        while (SpawnIndex <= SpawnPoints.Length) { SpawnBlocks(); yield return new WaitForSeconds(0.1f); }
    }

    void SpawnBlocks()
    {
        int RangeLowest;        // Choose random lowest number. 
        int RangeHighest = 0;   // Choose random highest number.
        float RangeBetween = Random.Range(0, 100);  // Choose a random number inbetween RangeHighest and RangeLowest.

        if (SpawnIndex <= SpawnPoints.Length)
        {
            switch (ManagerGame.Difficulty)
            {
                case ManagerGame.DifficultyState.Beginner:
                    ManagerGame.BallSpeed = 100;
                    for (int i = 0; i < Beginner.Length; i++)
                    {
                        RangeLowest = RangeHighest;
                        RangeHighest += Beginner[i].SpawnChance;
                        if (RangeBetween >= RangeLowest && RangeBetween < RangeHighest && SpawnIndex < SpawnPoints.Length)
                        {   // If RangeBetween is below the highest and lowest, and SpawnIndex to remove IndexOutOfBounds.
                            Transform PositionToSpawn = SpawnPoints[SpawnIndex];    // Random prefab at SpawnPoints.position.
                            Instantiate(Beginner[i].BlockPrefab, PositionToSpawn.position, Quaternion.identity);
                            SpawnIndex++;
                        }
                    }
                    break;

                case ManagerGame.DifficultyState.Easy:
                    ManagerGame.BallSpeed = 120;
                    for (int i = 0; i < Easy.Length; i++)
                    {
                        RangeLowest = RangeHighest;
                        RangeHighest += Easy[i].SpawnChance;
                        if (RangeBetween >= RangeLowest && RangeBetween < RangeHighest && SpawnIndex < SpawnPoints.Length)
                        {
                            Transform PositionToSpawn = SpawnPoints[SpawnIndex];
                            Instantiate(Easy[i].BlockPrefab, PositionToSpawn.position, Quaternion.identity);
                            SpawnIndex++;
                        }
                    }
                    break;

                case ManagerGame.DifficultyState.Medium:
                    ManagerGame.BallSpeed = 140;
                    for (int i = 0; i < Medium.Length; i++)
                    {
                        RangeLowest = RangeHighest;
                        RangeHighest += Medium[i].SpawnChance;
                        if (RangeBetween >= RangeLowest && RangeBetween < RangeHighest && SpawnIndex < SpawnPoints.Length)
                        {
                            Transform PositionToSpawn = SpawnPoints[SpawnIndex];
                            Instantiate(Medium[i].BlockPrefab, PositionToSpawn.position, Quaternion.identity);
                            SpawnIndex++;
                        }
                    }
                    break;

                case ManagerGame.DifficultyState.Hard:
                    ManagerGame.BallSpeed = 160;
                    for (int i = 0; i < Hard.Length; i++)
                    {
                        RangeLowest = RangeHighest;
                        RangeHighest += Hard[i].SpawnChance;
                        if (RangeBetween >= RangeLowest && RangeBetween < RangeHighest && SpawnIndex < SpawnPoints.Length)
                        {
                            Transform PositionToSpawn = SpawnPoints[SpawnIndex];
                            Instantiate(Hard[i].BlockPrefab, PositionToSpawn.position, Quaternion.identity);
                            SpawnIndex++;
                        }
                    }
                    break;
            }
        }
    }
}