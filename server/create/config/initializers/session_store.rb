# Be sure to restart your server when you modify this file.

# Your secret key for verifying cookie session data integrity.
# If you change this key, all old sessions will become invalid!
# Make sure the secret is at least 30 characters and all random, 
# no regular words or you'll be exposed to dictionary attacks.
ActionController::Base.session = {
  :key         => '_create_session',
  :secret      => 'feeb43b27cceb437118d40f9a4a83912fad7726226f921f0859b20ed2be9cf64e9ccac6a4d40df6b88a13db5020ccf7e246df632c0d112884abf152cf3a1f494'
}

# Use the database for sessions instead of the cookie-based default,
# which shouldn't be used to store highly confidential information
# (create the session table with "rake db:sessions:create")
# ActionController::Base.session_store = :active_record_store
