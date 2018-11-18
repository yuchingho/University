using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ManagerSpawn : MonoBehaviour {

    ManagerGame ManagerGame;    // Getting ManagerGame.cs
    public Text DifficultyUI;
    public enum SpawnState { Spawning, Waiting };
    public SpawnState State = SpawnState.Waiting;
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

    void Start()
    {
        ManagerGame = GameObject.Find("ManagerGame").GetComponent<ManagerGame>();
        SpawnIndex = 0;
        // Initalising SpawnLoop().
        StartCoroutine(SpawnLoop());
    }

    void Update()
    {   // Counting all Blocks in scene, then checking if Blocks have been destroyed.
        GameObject[] ActiveBlocks = GameObject.FindGameObjectsWithTag("block");
        if (ActiveBlocks.Length <= 0)
        {   // If all Blocks destroyed, reset SpawnIndex so SpawnLoop() can run again.
            SpawnIndex = 0;
            StartCoroutine(SpawnLoop());
        }
        DifficultyUI.text = ManagerGame.Difficulty.ToString();
    }

    IEnumerator SpawnLoop()
    {   // While SpawnIndex is less than 27, SpawnBlocks will run.
        while (SpawnIndex <= 27) { SpawnBlocks(); yield return new WaitForSeconds(0.1f); }
    }

    void SpawnBlocks()
    {
        State = SpawnState.Spawning;
        int RangeLowest;        // Choose random lowest number. 
        int RangeHighest = 0;   // Choose random highest number.
        float RangeBetween = Random.Range(0, 100);  // Choose a random number inbetween highest and lowest.

        // player not being able to move while it's spawning

        if (State == SpawnState.Spawning && SpawnIndex <= 27)
        {
            switch (ManagerGame.Difficulty)
            {
                case ManagerGame.DifficultyState.Beginner:
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
                        else { State = SpawnState.Waiting; }
                    }
                    break;

                case ManagerGame.DifficultyState.Easy:
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
                        else { State = SpawnState.Waiting; }
                    }
                    break;

                case ManagerGame.DifficultyState.Medium:

                    break;

                case ManagerGame.DifficultyState.Hard:

                    break;
            }
        }
    }
}