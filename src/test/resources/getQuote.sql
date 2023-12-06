/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  scott
 * Created: Dec 5, 2023
 */

insert into SrcVal(SrcCde, SrcTxt) values(1, 'My first source');

insert into Quote(QuoteNum, QuoteTxt, CanUse, SrcCde) values(25, 'I am a quote', 'Y', 1);