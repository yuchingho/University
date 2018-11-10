using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ManagerSpawn : MonoBehaviour {

    ManagerGame ManagerGame;    // Getting ManagerGame.cs
    public bool BlocksFilled;

    [System.Serializable]
    public class SettingDifficulty
    {
        public string Name;
        public GameObject Block;
        public int SpawnChance;
    }

    public enum SpawnState { Counting, Spawning, Waiting };
    public SpawnState State = SpawnState.Counting;
    public SettingDifficulty[] Beginner;
    public SettingDifficulty[] Easy;

    void Start()
    {
        ManagerGame = GameObject.Find("ManagerGame").GetComponent<ManagerGame>();
        BlocksFilled = false;
    }
	
	void Update()
    {   // Counting all Blocks in scene, then checking if Blocks have been destroyed, resetting BlocksFilled.
        GameObject[] ActiveBlocks = GameObject.FindGameObjectsWithTag("block");
        if (ActiveBlocks.Length == 0) { BlocksFilled = false; }

        if (State != SpawnState.Spawning && BlocksFilled == false)
        {
            switch (ManagerGame.Difficulty)
            {
                case ManagerGame.DifficultyState.Beginner:
                    StartCoroutine(SpawnBlocks(Beginner[0]));
                    //SpawnBlocks(Beginner[0]) = SpawnBlocks.SpawnChance;
                    break;
            }


            /*
            if (ManagerGame.Difficulty == ManagerGame.DifficultyState.Beginner)
            {
                StartCoroutine(SpawnBlocks(Beginner[0])); // still need the spawn percentage
            }

            if (ManagerGame.Difficulty == ManagerGame.DifficultyState.Easy)
            {   // Change Beginner[0] to Easy
                StartCoroutine(SpawnBlocks(Beginner[0])); // still need the spawn percentage
            }
            */
        }
    }

    IEnumerator SpawnBlocks (SettingDifficulty _Difficulty)
    {
        State = SpawnState.Spawning;
        for (int y =   8; y >=  4; y = y - 2)    // y =  8,
        for (int x = -20; x <= 20; x = x + 5)    // x = 20
        {   // Spawning 27 blocks.
            Instantiate(_Difficulty.Block, new Vector2(x, y), Quaternion.identity);
            yield return new WaitForSeconds(0.1f);
        }
        BlocksFilled = true;
        State = SpawnState.Waiting;
        yield break;
    }   
}