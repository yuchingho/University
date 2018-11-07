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
        public int SpawnPercent;
    }

    public enum SpawnState { Spawning, Waiting, Counting };
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

        if (ManagerGame.Difficulty == ManagerGame.DifficultyState.Beginner && State != SpawnState.Spawning && BlocksFilled == false)
        {
            StartCoroutine(SpawnBlocks(Beginner[0])); // still need the spawn percentage
            // https://answers.unity.com/questions/225985/spawn-system-working-but-not-the-way-i-want.html
        }
    }

    IEnumerator SpawnBlocks (SettingDifficulty _Difficulty)
    {
        State = SpawnState.Spawning;  
        for (int x = -20; x <= 10; x = x + 5)   // x = 20,
        for (int y =   4; y <= 4; y = y + 2)    // y = 10
        {   // Spawning 36 blocks.
            Instantiate(_Difficulty.Block, new Vector2(x, y), Quaternion.identity);
            yield return new WaitForSeconds(0.1f);
        }
        BlocksFilled = true;
        State = SpawnState.Waiting;
        yield break;
    }

    // switch case, if below number of points, start, then easy, etc, percentage probability show up
    public void Difficulty()
    {

    }
    
}