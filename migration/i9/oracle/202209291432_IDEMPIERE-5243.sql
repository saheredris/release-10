-- IDEMPIERE-5243
SELECT register_migration_script('202209291432_IDEMPIERE-5243.sql') FROM dual;

SET SQLBLANKLINES ON
SET DEFINE OFF

-- Sep 29, 2022, 2:32:41 PM CEST
UPDATE AD_Column SET FieldLength=4000, AD_Reference_ID=10,Updated=TO_TIMESTAMP('2022-09-29 14:32:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=193
;

-- Sep 29, 2022, 2:32:45 PM CEST
ALTER TABLE AD_Val_Rule MODIFY Code VARCHAR2(4000 CHAR) DEFAULT NULL 
;

-- Sep 29, 2022, 2:33:03 PM CEST
UPDATE AD_Field SET SeqNo=10,Updated=TO_TIMESTAMP('2022-09-29 14:33:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=278
;

-- Sep 29, 2022, 2:33:03 PM CEST
UPDATE AD_Field SET SeqNo=20,Updated=TO_TIMESTAMP('2022-09-29 14:33:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=1991
;

-- Sep 29, 2022, 2:33:03 PM CEST
UPDATE AD_Field SET SeqNo=30,Updated=TO_TIMESTAMP('2022-09-29 14:33:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=194
;

-- Sep 29, 2022, 2:33:03 PM CEST
UPDATE AD_Field SET SeqNo=40,Updated=TO_TIMESTAMP('2022-09-29 14:33:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=195
;

-- Sep 29, 2022, 2:33:03 PM CEST
UPDATE AD_Field SET SeqNo=50,Updated=TO_TIMESTAMP('2022-09-29 14:33:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=277
;

-- Sep 29, 2022, 2:33:03 PM CEST
UPDATE AD_Field SET SeqNo=60,Updated=TO_TIMESTAMP('2022-09-29 14:33:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=5812
;

-- Sep 29, 2022, 2:33:03 PM CEST
UPDATE AD_Field SET SeqNo=70,Updated=TO_TIMESTAMP('2022-09-29 14:33:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=198
;

-- Sep 29, 2022, 2:33:03 PM CEST
UPDATE AD_Field SET SeqNo=80, NumLines=8,Updated=TO_TIMESTAMP('2022-09-29 14:33:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=199
;

-- Sep 29, 2022, 2:33:03 PM CEST
UPDATE AD_Field SET SeqNo=0,Updated=TO_TIMESTAMP('2022-09-29 14:33:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=204621
;

-- Sep 29, 2022, 2:33:03 PM CEST
UPDATE AD_Field SET SeqNo=0,Updated=TO_TIMESTAMP('2022-09-29 14:33:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=193
;

