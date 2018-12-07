using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BlockValue : MonoBehaviour {

    ManagerGame ManagerGame;        // Getting the ManagerGame Script.
    Ball BallScript;                // Getting the BallScript Script.
    public BlockSpecs BlockSpecs;   // Getting the BlockSpecs Script. (Class - Scriptable Object)
    public string FoodType;         // Getting the BlockSpecs FoodType.
    public int PointValue;          // Getting the BlockSpecs PointValue.

    void Start()
    {
        ManagerGame = GameObject.Find("ManagerGame").GetComponent<ManagerGame>();
        BallScript = GameObject.Find("Ball").GetComponent<Ball>();
        FoodType = BlockSpecs.FoodType.ToString();
        PointValue = BlockSpecs.PointValue;
        // PointValue doubles || triples depending on game difficulty.
        if (ManagerGame.Difficulty == ManagerGame.DifficultyState.Medium) { PointValue = PointValue * 2; }
        if (ManagerGame.Difficulty == ManagerGame.DifficultyState.Hard)   { PointValue = PointValue * 3; }
    }

    void OnCollisionEnter2D(Collision2D collision)
    {   // Adds PointValue to ScoreCurrent when Ball destroys Blocks. Also checks the Boolean values checklist.
        if (collision.gameObject.name == "Ball")
        {
            // CheckWhite and FoodBlock Default.
            if (BallScript.CheckDefault == true && BlockSpecs.FoodType == BlockSpecs.Food.Default)
            {
                ManagerGame.ScoreCurrent = ManagerGame.ScoreCurrent + PointValue;
                Destroy(this.gameObject);
            }

            // CheckWhite and FoodBlock Rest except for Bad.
            if (BallScript.CheckDefault == true && BlockSpecs.FoodType != BlockSpecs.Food.Default)
            {
                ManagerGame.ScoreCurrent = ManagerGame.ScoreCurrent + PointValue; // edit value for destroying a good block
                Destroy(this.gameObject);
            }

            // CheckWhite and FoodBlock Bad.
            if (BallScript.CheckDefault == true && BlockSpecs.FoodType == BlockSpecs.Food.Bad)
            {
                ManagerGame.ScoreCurrent = ManagerGame.ScoreCurrent + PointValue;
                Destroy(this.gameObject);
            }

            // currently only got white

        }
    }
}