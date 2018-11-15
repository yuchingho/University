using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ManagerSpawn : MonoBehaviour {

    ManagerGame ManagerGame;    // Getting ManagerGame.cs
    public bool BlocksFilled;
    public enum SpawnState { Spawning, Waiting };
    public SpawnState State = SpawnState.Waiting;
    public Transform[] SpawnPoints;
    // WHY DOES THIS WORK???????????????????????????????????????????????????????
    [System.Serializable]
    public class Blocks
    {
        public string Name;
        public GameObject Block;
        public int Spawn;
    }

    public Blocks[] Beginner = new Blocks[6];

    void Start()
    {
        ManagerGame = GameObject.Find("ManagerGame").GetComponent<ManagerGame>();
        BlocksFilled = false;
        DifficultySet();
        StartCoroutine(SpawnLoop());
    }

    IEnumerator SpawnLoop()
    {
        while (true)
        {
            SpawnBlocks();
            yield return new WaitForSeconds(1);
        }
    }

    void Update()
    {   // Counting all Blocks in scene, then checking if Blocks have been destroyed, resetting BlocksFilled.
        GameObject[] ActiveBlocks = GameObject.FindGameObjectsWithTag("block");
        if (ActiveBlocks.Length == 0)
        {
            BlocksFilled = false;
            SpawnBlocks();
        }
        else
        {
            BlocksFilled = true;
        }
    }

    void SpawnBlocks()
    {
        float random = Random.Range(0, 100);
        int lowLim;
        int hiLim = 0;
        for (int i = 0; i < Beginner.Length; i++)
        {
            lowLim = hiLim;
            hiLim += Beginner[i].Spawn;
            if (random >= lowLim && random < hiLim)
            {
                Debug.Log(random);
                Transform Blockobj = SpawnPoints[Random.Range(0, SpawnPoints.Length)];
                Instantiate(Beginner[i].Block, Blockobj.position, Blockobj.rotation);
            }
        }
    }

    void DifficultySet()
    {
        if (State == SpawnState.Waiting && BlocksFilled == false)
        {
            switch (ManagerGame.Difficulty)
            {
                case ManagerGame.DifficultyState.Beginner:
                    //Instantiate(Beginner[0], SpawnPoints[0].transform.position, Quaternion.identity);
                    Beginner[0].Spawn = 90;
                    Beginner[1].Spawn = 2;
                    Beginner[2].Spawn = 2;
                    Beginner[3].Spawn = 2;
                    Beginner[4].Spawn = 2;
                    Beginner[5].Spawn = 2;
                    break;
            }
        }
    }
}