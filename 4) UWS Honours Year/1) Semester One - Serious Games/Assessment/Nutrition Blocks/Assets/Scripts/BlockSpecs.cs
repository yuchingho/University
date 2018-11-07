using System.Collections;
using System.Collections.Generic;
using UnityEngine;
[CreateAssetMenu (fileName = "BlockSpecs", menuName = "Block")]
public class BlockSpecs : ScriptableObject {

    public enum Food { Default, Fruit, Grains, Dairy, Meat, Bad};
    public Food FoodType = Food.Default;
    public int PointValue;
}
// Using ScriptableObject because "tag" used to check any Blocks left.