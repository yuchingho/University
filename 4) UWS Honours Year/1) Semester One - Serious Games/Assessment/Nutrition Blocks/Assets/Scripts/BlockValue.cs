using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class BlockValue : MonoBehaviour {

    ManagerGame ManagerGame;        // Getting the ManagerGame Script.
    Ball BallScript;                // Getting the BallScript Script.
    public BlockSpecs BlockSpecs;   // Getting the BlockSpecs Script. (Class - Scriptable Object)
    public string BlockType;        // Getting the BlockSpecs FoodType.
    public string FoodName;         // Not from BlocksSpecs Script. Each Prefab has different FoodName.
    public int PointValue;          // Not from BlocksSpecs Script. Each Prefab has different PointValue.

    void Start()
    {
        ManagerGame = GameObject.Find("ManagerGame").GetComponent<ManagerGame>();
        BallScript = GameObject.Find("Ball").GetComponent<Ball>();
        BlockType = BlockSpecs.FoodType.ToString();
    }

    void OnCollisionEnter2D(Collision2D collision)
    {   // Adds PointValue to ScoreCurrent when Ball destroys Blocks. Also checks the Boolean values checklist.
        if (collision.gameObject.name == "Ball")
        {
            // CheckWhite and FoodBlock Default.
            if (BallScript.CheckDefault == true && BlockSpecs.FoodType == BlockSpecs.Food.Default)
            {
                ManagerGame.UIDeadType.text = "Type: [ Null ]";
                ManagerGame.UIDeadName.text = FoodName.ToString();
                ManagerGame.UIDeadPoints.text = "Value: " + PointValue.ToString();
                ManagerGame.ScoreCurrent = ManagerGame.ScoreCurrent + PointValue;
                Destroy(this.gameObject);
            }

            // CheckFruit and FoodBlock Fruit.
            else if (BallScript.CheckFruit == true && BlockSpecs.FoodType == BlockSpecs.Food.Fruit)
            {
                ManagerGame.UIDeadType.text = "Type: Fruits / Veg";
                ManagerGame.UIDeadName.text = FoodName.ToString();
                if (ManagerGame.ScoreCurrent <= 2000) { ManagerGame.UIDeadPoints.text = "Value: " + PointValue.ToString(); }
                else { ManagerGame.UIDeadPoints.text = "Calorie: " + PointValue.ToString(); }
                ManagerGame.ScoreCurrent = ManagerGame.ScoreCurrent + PointValue;
                Destroy(this.gameObject);
            }

            // CheckGrains and FoodBlock Grains.
            else if (BallScript.CheckGrains == true && BlockSpecs.FoodType == BlockSpecs.Food.Grains)
            {
                ManagerGame.UIDeadType.text = "Type: Starch";
                ManagerGame.UIDeadName.text = FoodName.ToString();
                if (ManagerGame.ScoreCurrent <= 2000) { ManagerGame.UIDeadPoints.text = "Value: " + PointValue.ToString(); }
                else { ManagerGame.UIDeadPoints.text = "Calorie: " + PointValue.ToString(); }
                ManagerGame.ScoreCurrent = ManagerGame.ScoreCurrent + PointValue;
                Destroy(this.gameObject);
            }

            // CheckDairy and FoodBlock Dairy.
            else if (BallScript.CheckDairy == true && BlockSpecs.FoodType == BlockSpecs.Food.Dairy)
            {
                ManagerGame.UIDeadType.text = "Type: " + BlockType.ToString();
                ManagerGame.UIDeadName.text = FoodName.ToString();
                if (ManagerGame.ScoreCurrent <= 2000) { ManagerGame.UIDeadPoints.text = "Value: " + PointValue.ToString(); }
                else { ManagerGame.UIDeadPoints.text = "Calorie: " + PointValue.ToString(); }
                ManagerGame.ScoreCurrent = ManagerGame.ScoreCurrent + PointValue;
                Destroy(this.gameObject);
            }

            // CheckMeat and FoodBlock Meat.
            else if (BallScript.CheckMeat == true && BlockSpecs.FoodType == BlockSpecs.Food.Meat)
            {
                ManagerGame.UIDeadType.text = "Type: Proteins";
                ManagerGame.UIDeadName.text = FoodName.ToString();
                if (ManagerGame.ScoreCurrent <= 2000) { ManagerGame.UIDeadPoints.text = "Value: " + PointValue.ToString(); }
                else { ManagerGame.UIDeadPoints.text = "Calorie: " + PointValue.ToString(); }
                ManagerGame.ScoreCurrent = ManagerGame.ScoreCurrent + PointValue;
                Destroy(this.gameObject);
            }

            // CheckBad and FoodBlock Bad.
            else if (BallScript.CheckBad == true && BlockSpecs.FoodType == BlockSpecs.Food.Bad)
            {
                ManagerGame.UIDeadType.text = "Type: Junk Food";
                ManagerGame.UIDeadName.text = FoodName.ToString();
                if (ManagerGame.ScoreCurrent <= 2000) { ManagerGame.UIDeadPoints.text = "Value: " + PointValue.ToString(); }
                else { ManagerGame.UIDeadPoints.text = "Calorie: " + PointValue.ToString(); }
                ManagerGame.ScoreCurrent = ManagerGame.ScoreCurrent + PointValue;
                Destroy(this.gameObject);
            }
        }
    }
}