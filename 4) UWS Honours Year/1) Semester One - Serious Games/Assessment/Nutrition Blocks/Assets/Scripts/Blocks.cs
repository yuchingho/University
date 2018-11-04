using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Blocks : MonoBehaviour {

    public bool Empty;
    public GameObject[] Easy;

    void Start()
    {
        Empty = false;
        /* Row01 --------------------------------------------------------*/
        Instantiate(Easy[0], new Vector2(-20, 3.45f), Quaternion.identity);
        Instantiate(Easy[1], new Vector2(-15, 3.45f), Quaternion.identity);
        Instantiate(Easy[2], new Vector2(-10, 3.45f), Quaternion.identity);
        Instantiate(Easy[3], new Vector2( -5, 3.45f), Quaternion.identity);
        Instantiate(Easy[4], new Vector2(  0, 3.45f), Quaternion.identity);
        Instantiate(Easy[5], new Vector2(  5, 3.45f), Quaternion.identity);
        Instantiate(Easy[0], new Vector2( 10, 3.45f), Quaternion.identity);
        Instantiate(Easy[1], new Vector2( 15, 3.45f), Quaternion.identity);
        Instantiate(Easy[2], new Vector2( 20, 3.45f), Quaternion.identity);
        /* Row01 --------------------------------------------------------*/

        /* Row02 --------------------------------------------------------*/
        Instantiate(Easy[0], new Vector2(-20, 5.45f), Quaternion.identity);
        Instantiate(Easy[1], new Vector2(-15, 5.45f), Quaternion.identity);
        Instantiate(Easy[2], new Vector2(-10, 5.45f), Quaternion.identity);
        Instantiate(Easy[3], new Vector2( -5, 5.45f), Quaternion.identity);
        Instantiate(Easy[4], new Vector2(  0, 5.45f), Quaternion.identity);
        Instantiate(Easy[5], new Vector2(  5, 5.45f), Quaternion.identity);
        Instantiate(Easy[0], new Vector2( 10, 5.45f), Quaternion.identity);
        Instantiate(Easy[1], new Vector2( 15, 5.45f), Quaternion.identity);
        Instantiate(Easy[2], new Vector2( 20, 5.45f), Quaternion.identity);
        /* Row02 --------------------------------------------------------*/

        /* Row03 --------------------------------------------------------*/
        Instantiate(Easy[0], new Vector2(-20, 7.45f), Quaternion.identity);
        Instantiate(Easy[1], new Vector2(-15, 7.45f), Quaternion.identity);
        Instantiate(Easy[2], new Vector2(-10, 7.45f), Quaternion.identity);
        Instantiate(Easy[3], new Vector2( -5, 7.45f), Quaternion.identity);
        Instantiate(Easy[4], new Vector2(  0, 7.45f), Quaternion.identity);
        Instantiate(Easy[5], new Vector2(  5, 7.45f), Quaternion.identity);
        Instantiate(Easy[0], new Vector2( 10, 7.45f), Quaternion.identity);
        Instantiate(Easy[1], new Vector2( 15, 7.45f), Quaternion.identity);
        Instantiate(Easy[2], new Vector2( 20, 7.45f), Quaternion.identity);
        /* Row03 --------------------------------------------------------*/

        /* Row04 --------------------------------------------------------*/
        Instantiate(Easy[0], new Vector2(-20, 9.45f), Quaternion.identity);
        Instantiate(Easy[1], new Vector2(-15, 9.45f), Quaternion.identity);
        Instantiate(Easy[2], new Vector2(-10, 9.45f), Quaternion.identity);
        Instantiate(Easy[3], new Vector2( -5, 9.45f), Quaternion.identity);
        Instantiate(Easy[4], new Vector2(  0, 9.45f), Quaternion.identity);
        Instantiate(Easy[5], new Vector2(  5, 9.45f), Quaternion.identity);
        Instantiate(Easy[0], new Vector2( 10, 9.45f), Quaternion.identity);
        Instantiate(Easy[1], new Vector2( 15, 9.45f), Quaternion.identity);
        Instantiate(Easy[2], new Vector2( 20, 9.45f), Quaternion.identity);
        /* Row04 --------------------------------------------------------*/

    }

    void Update() 
    {
        if (Empty == false)
        {

        }
		
	}
}