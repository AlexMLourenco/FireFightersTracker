<template>
  <v-container
    fluid
    grid-list-xl
    fill-height>
    <v-layout
      justify-center
      align-center
    >
      <v-flex xs12>
        <material-card
          color="green">
          <div
            slot="header"
          >
            <div class="title font-weight-light mb-2">Notifications</div>
            <div class="category">
              List of notifications

            </div>
          </div>

          
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import materialCard from '~/components/material/AppCard'
  import materialNotification from '~/components/material/AppNotification'

  export default {
    layout: 'dashboard',
    components: {
      materialCard,
      materialNotification
    },
    data: () => ({
      color: null,
      colors: [
        'purple',
        'info',
        'success',
        'warning',
        'error'
      ],
      top: true,
      bottom: false,
      left: false,
      right: false,
      snackbar: false
    }),

    methods: {
      snack (...args) {
        this.top = false
        this.bottom = false
        this.left = false
        this.right = false

        for (const loc of args) {
          this[loc] = true
        }

        this.color = this.colors[Math.floor(Math.random() * this.colors.length)]

        this.snackbar = true
      }
    }
  }
</script>
