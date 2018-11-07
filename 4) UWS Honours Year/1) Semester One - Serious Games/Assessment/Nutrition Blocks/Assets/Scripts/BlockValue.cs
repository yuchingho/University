using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BlockValue : MonoBehaviour {

    ManagerGame ManagerGame;    // Getting ManagerGame.cs
    Ball BallScript;
    public BlockSpecs BlockSpecs;
    public string FoodType;
    public int PointValue;

    void Start()
    {
        ManagerGame = GameObject.Find("ManagerGame").GetComponent<ManagerGame>();
        BallScript = GameObject.Find("Ball").GetComponent<Ball>();
        FoodType = BlockSpecs.FoodType.ToString();  // Displaying BlockSpec's enum.
        PointValue = BlockSpecs.PointValue;         // Displaying BlockSpec's Value.

        // Different values for different difficulties.
        if (ManagerGame.Difficulty == ManagerGame.DifficultyState.Easy) { PointValue = PointValue * 2; }
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
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