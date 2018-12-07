using System.Collections;
using System.Collections.Generic;
using UnityEngine;
[CreateAssetMenu (fileName = "Specs FoodType", menuName = "Food Block")]
public class BlockSpecs : ScriptableObject {

    public enum Food { Default, Fruit, Grains, Dairy, Meat, Bad};
    public Food FoodType = Food.Default;
    public int PointValue;
}
// Using ScriptableObject Class because "CompareTag" is used to check if any Blocks left not destroyed by Ball.